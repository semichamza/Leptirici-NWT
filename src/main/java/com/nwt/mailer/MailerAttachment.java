package com.nwt.mailer;

/**
 * Created by Enit on 18-Nov-14.
 */
public class MailerAttachment implements MailerAttachmentInterface {
    private String fileName;
    private byte[] fileBody;


    public MailerAttachment(String fileName, byte[] fileBody) {
        setFileBody(fileBody);
        setFileName(fileName);
    }

    public MailerAttachment() {

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
