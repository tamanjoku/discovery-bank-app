package za.co.discovery.bank.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.bank.app.data.model.reporting.FinancialPositionReport;
import za.co.discovery.bank.app.data.model.reporting.TransactionalAccountReport;
import za.co.discovery.bank.app.enums.ResponseEnum;
import za.co.discovery.bank.app.rest.response.ReportDataHolderResponse;
import za.co.discovery.bank.app.service.ReportingService;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/reporting")
public class ReportingApiController {

    private final ReportingService reportingService;
    private final MessageSource messageSource;

    private static final Locale locale = Locale.UK;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportingApiController.class);

    public ReportingApiController(final ReportingService reportingService, final MessageSource messageSource) {
        this.reportingService = reportingService;
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/financial-position-report", method = RequestMethod.POST)
    public ResponseEntity<ReportDataHolderResponse<FinancialPositionReport>> financialPositionReport() {

        LOGGER.debug("Calling financialPositionReport");

        List<FinancialPositionReport> reportData = reportingService.getFinancialPositionReport();

        ReportDataHolderResponse<FinancialPositionReport> response = new ReportDataHolderResponse(
                ResponseEnum.SUCCESSFUL.getCode(),
                messageSource.getMessage(ResponseEnum.SUCCESSFUL.getLabelKey(),
                        new Object[]{messageSource.getMessage("report.generation.successful", null, locale)},
                        locale),
                ResponseEnum.SUCCESSFUL.getHttpCode().value(),
                new Date().getTime(),
                reportData
        );

        return new ResponseEntity<>(response, ResponseEnum.SUCCESSFUL.getHttpCode());
    }

    @RequestMapping(value = "/transactional-account-report", method = RequestMethod.POST)
    public ResponseEntity<ReportDataHolderResponse<TransactionalAccountReport>> transactionalAccountReport() {

        LOGGER.debug("Calling transactionalAccountReport");

        List<TransactionalAccountReport> reportData = reportingService.getTransactionalAccountsReport();

        ReportDataHolderResponse<TransactionalAccountReport> response = new ReportDataHolderResponse(
                ResponseEnum.SUCCESSFUL.getCode(),
                messageSource.getMessage(ResponseEnum.SUCCESSFUL.getLabelKey(),
                        new Object[]{messageSource.getMessage("report.generation.successful", null, locale)},
                        locale),
                ResponseEnum.SUCCESSFUL.getHttpCode().value(),
                new Date().getTime(),
                reportData
        );

        return new ResponseEntity<>(response, ResponseEnum.SUCCESSFUL.getHttpCode());
    }
}
