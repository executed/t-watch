package com.devserbyn.twatch.service.parser.impl;

import com.devserbyn.twatch.service.parser.Currency;
import com.devserbyn.twatch.service.parser.CurrencyParser;
import com.devserbyn.twatch.utility.ParserUtil;
import com.devserbyn.twatch.utility.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyParserImpl extends BaseParser implements CurrencyParser {

    public CurrencyParserImpl(ParserUtil parserUtil, WebUtil webUtil) {
        super(parserUtil, webUtil);
    }

    @Override
    public String parseCurrencyValues(Currency firstCurrency, Currency secondCurrency) {
        String url = "https://finance.i.ua";
        String xPath = "/html/body/div[3]/div[3]/div/div[1]/div[1]/div[1]/div/table/tbody/tr[1]/td[3]/span/span[1]";

        return this.processXPath(url, xPath);
    }
}
