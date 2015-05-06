package com.nwt.mailer;

/**
 * Created by Enit on 18-Nov-14.
 */
public class MailerAttachmentImpl implements MailerAttachment
{
    private String fileName;
    private byte[] fileBody;


    public MailerAttachmentImpl(String fileName, byte[] fileBody)
    {
        setFileBody(fileBody);
        setFileName(fileName);
    }

    public MailerAttachmentImpl()
    {

    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public byte[] getFileBody() {
        return fileBody;
    }

    public void setFileBody(byte[] fileBody) {
        this.fileBody = fileBody;
    }
}
