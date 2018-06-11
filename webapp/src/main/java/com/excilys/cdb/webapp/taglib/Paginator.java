package com.excilys.cdb.webapp.taglib;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;



public class Paginator extends SimpleTagSupport {
    private String uri;
    private int currPage;
    private int totalPages;
    private int maxLinks = 10;

    /**
     * .
     * @return a
     */
    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();

        boolean lastPage = currPage == totalPages;
        int pgStart = Math.max(currPage - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;
        if (pgEnd > totalPages + 1) {
            int diff = pgEnd - totalPages;
            pgStart -= diff - 1;
            if (pgStart < 1) {
                pgStart = 1;
            }
            pgEnd = totalPages + 1;
        }

        try {
            out.write("<ul class=\"pagination\">");

            if (currPage > 1) {
                out.write(constructLink(currPage - 1, "&laquo;", null));
            }

            for (int i = pgStart; i < pgEnd; i++) {
                out.write(constructLink(i));
            }

            if (!lastPage) {
                out.write(constructLink(currPage + 1, "&raquo;", null));
            }

            out.write("</ul>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }

    /**
     * .
     * @param page a
     * @return a
     */
    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }

    /**
     * .
     * @param page page
     * @param text text
     * @param className classname
     * @return link
     */
    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        link.append(">").append("<a href=\"").append(uri.replace("##", String.valueOf(page))).append("\">").append(text)
                .append("</a></li>");
        return link.toString();
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }
}