package com.myrunning.leaderboard.tags;

/**
 *  File: RDBErrorsTag.java
 *  Author:  Joshua Forester
 *  Date: 2009/12/28
 *  Description: Taglib to generate error custom RDB errors content.
 **/


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTag;
import org.springframework.util.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.tags.form.ErrorsTag;
import org.springframework.web.servlet.tags.form.TagWriter;
import org.springframework.web.util.ExpressionEvaluationUtils;


public final class RDBErrorsTag extends ErrorsTag implements BodyTag {

    private String rdbElement = "img";
    private String springElement;
    private Object message;
    private String code;
    private String text;


    public RDBErrorsTag() {
	setSpringElement(getElement());
    }


    /**
     * Set the MessageSourceResolvable for this tag.
     * Accepts a direct MessageSourceResolvable instance as well as a JSP
     * expression language String that points to a MessageSourceResolvable.
     * <p>If a MessageSourceResolvable is specified, it effectively overrides
     * any code, arguments or text specified on this tag.
     */
    public void setMessage(Object message) {
	this.message = message;
    }

    public void setSpringElement(String springElement) {
	this.springElement=springElement;
    }

    public String getSpringElement() {
	return springElement;
    }
    
    /**
     * Set the message code for this tag.
     */
    public void setCode(String code) {
	this.code = code;
    }
    
    /**
     * Set the message text for this tag.
     */
    public void setText(String text) {
	this.text = text;
    }


    @Override
    protected void renderDefaultContent(TagWriter tagWriter) throws JspException {
	setElement(rdbElement);

	tagWriter.startTag(getElement());

	//WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());

	// Resolve the unescaped message.
	String msg = resolveMessage();

	// tagWriter.writeAttribute("src", "http://lh6.ggpht.com/_Njaf48TXR0g/SxnFnZVL_UI/AAAAAAAAAjc/9w5qTnvqjYk/status_skipped.jpg");
	tagWriter.writeAttribute("src", msg);
	tagWriter.writeAttribute("class", "rdb_err");
	tagWriter.writeAttribute("alt", "");
	tagWriter.writeAttribute("title", "");
	
	setElement(getSpringElement());

	super.renderDefaultContent(tagWriter);

	/*
	//writeDefaultAttributes(tagWriter);
	String delimiter = ObjectUtils.getDisplayString(evaluate("delimiter", getDelimiter()));
	String[] errorMessages = getBindStatus().getErrorMessages();
	for (int i = 0; i < errorMessages.length; i++) {
	    String errorMessage = errorMessages[i];
	    if (i > 0) {
		tagWriter.appendValue(delimiter);
	    }
	    tagWriter.appendValue(getDisplayString(errorMessage));
	}
	tagWriter.endTag();
	*/
    }


    /**
     * Resolve the specified message into a concrete message String.
     * The returned message String should be unescaped.
     */
    protected String resolveMessage() throws JspException, NoSuchMessageException {
	MessageSource messageSource = getMessageSource();
	if (messageSource == null) {
	    throw new JspTagException("No corresponding MessageSource found");
	}
	
	// Evaluate the specified MessageSourceResolvable, if any.
	MessageSourceResolvable resolvedMessage = null;
	if (this.message instanceof MessageSourceResolvable) {
	    resolvedMessage = (MessageSourceResolvable) this.message;
	} else if (this.message != null) {
	    String expr = this.message.toString();
	    resolvedMessage = (MessageSourceResolvable)
		ExpressionEvaluationUtils.evaluate("message", expr, MessageSourceResolvable.class, pageContext);
	}
	
	if (resolvedMessage != null) {
	    // We have a given MessageSourceResolvable.
	    return messageSource.getMessage(resolvedMessage, getRequestContext().getLocale());
	}
	
	String resolvedCode = ExpressionEvaluationUtils.evaluateString("code", this.code, pageContext);
	String resolvedText = ExpressionEvaluationUtils.evaluateString("text", this.text, pageContext);
	
	if (resolvedCode != null || resolvedText != null) {
	    // We have a code or default text that we need to resolve.

	    if (resolvedText != null) {
		// We have a fallback text to consider.
		return messageSource.getMessage(resolvedCode, 
						null, 
						resolvedText, 
						getRequestContext().getLocale());
	    } else {
		// We have no fallback text to consider.
		return messageSource.getMessage(resolvedCode, 
						null, 
						getRequestContext().getLocale());
	    }
	}
	
	// All we have is a specified literal text.
	return resolvedText;
    }


    
    /**
     * Use the current RequestContext's application context as MessageSource.
     */
    protected MessageSource getMessageSource() {
	return getRequestContext().getMessageSource();
    }

    
    
}//END OF CLASS RDBErrorsTag
