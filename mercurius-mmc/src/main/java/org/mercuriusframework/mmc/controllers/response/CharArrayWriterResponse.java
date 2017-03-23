package org.mercuriusframework.mmc.controllers.response;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Char array writer response
 */
public class CharArrayWriterResponse extends HttpServletResponseWrapper {
    /**
     * Char array
     */
    private final CharArrayWriter charArray = new CharArrayWriter();

    /**
     * Constructor
     * @param response Http-response
     */
    public CharArrayWriterResponse(HttpServletResponse response) {
        super(response);
    }

    /**
     * Get writer
     * @return Writer
     * @throws IOException Exception
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(charArray);
    }

    /**
     * Get output
     * @return String output
     */
    public String getOutput() {
        return charArray.toString();
    }
}