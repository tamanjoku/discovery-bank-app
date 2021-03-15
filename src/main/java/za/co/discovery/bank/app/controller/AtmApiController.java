package za.co.discovery.bank.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.bank.app.data.model.Denomination;
import za.co.discovery.bank.app.enums.ResponseEnum;
import za.co.discovery.bank.app.exception.AtmNotFundedException;
import za.co.discovery.bank.app.exception.DataNotFoundException;
import za.co.discovery.bank.app.exception.InvalidWithdrawalAccountException;
import za.co.discovery.bank.app.exception.LimitExceededException;
import za.co.discovery.bank.app.rest.request.WithdrawalDataHolderRequest;
import za.co.discovery.bank.app.rest.response.WithdrawalDataHolderResponse;
import za.co.discovery.bank.app.rest.response.entities.WithdrawalDispenseResponse;
import za.co.discovery.bank.app.service.ClientAccountService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/atm")
@Validated
public class AtmApiController {

    private final ClientAccountService clientAccountService;
    private final MessageSource messageSource;

    private static final Locale locale = Locale.UK;
    private static final Logger LOGGER = LoggerFactory.getLogger(AtmApiController.class);

    public AtmApiController(final ClientAccountService clientAccountService, final MessageSource messageSource) {
        this.clientAccountService = clientAccountService;
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/{atmId}/withdraw", method = RequestMethod.POST)
    public ResponseEntity<WithdrawalDataHolderResponse> withdraw(@PathVariable("atmId") Integer atmId, @Valid @RequestBody WithdrawalDataHolderRequest requestBody)
            throws DataNotFoundException, InvalidWithdrawalAccountException, LimitExceededException, AtmNotFundedException {

        LOGGER.debug("Calling withdraw from atm {} for client {} and account {}", atmId, requestBody.getClientId(), requestBody.getClientAccountNumber());

        Map<Denomination, Integer> dispensableAmounts = clientAccountService.withdraw(
                atmId, requestBody.getClientId(), requestBody.getClientAccountNumber(), requestBody.getWithdrawalAmount(), requestBody.getWithdrawalDate()
        );

        List<WithdrawalDispenseResponse> responseData = dispensableAmounts.entrySet().stream()
                .map(entry -> new WithdrawalDispenseResponse(entry.getKey().getValue(), entry.getValue()))
                .collect(Collectors.toList());

        WithdrawalDataHolderResponse response = new WithdrawalDataHolderResponse(
                ResponseEnum.SUCCESSFUL.getCode(),
                messageSource.getMessage(ResponseEnum.SUCCESSFUL.getLabelKey(),
                        new Object[]{messageSource.getMessage("atm.withdrawal.successful", new Object[] {requestBody.getWithdrawalAmount()}, locale)},
                        locale),
                ResponseEnum.SUCCESSFUL.getHttpCode().value(),
                new Date().getTime(),
                responseData
        );

        return new ResponseEntity<>(response, ResponseEnum.SUCCESSFUL.getHttpCode());
    }
}
