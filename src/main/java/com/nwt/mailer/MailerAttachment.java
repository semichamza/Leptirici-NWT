package com.nwt.mailer;

/**
 * Created by Enit on 18-Nov-14.
 */
public interface MailerAttachment
{
    String getFileName();

    byte[] getFileBody();
}
