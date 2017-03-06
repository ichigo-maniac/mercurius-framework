package org.mercuriusframework.dto;


/**
 * Currency entity data transfer object
 */
public class CurrencyEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = -3478738105755806302L;

    /**
     * Symbol
     */
    private String symbol;

    /**
     * Get symbol
     * @return Symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Set symbol
     * @param symbol Symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
