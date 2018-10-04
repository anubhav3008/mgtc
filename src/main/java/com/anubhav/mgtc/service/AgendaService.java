package com.anubhav.mgtc.service;

import com.anubhav.mgtc.dao.MeetingJsonDao;
import com.anubhav.mgtc.utils.VelocityUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;

@Service
public class AgendaService {
    @Autowired
    MeetingJsonDao meetingJsonDao;
    @Autowired
    VelocityUtil velocityUtil;

    ObjectMapper mapper = new ObjectMapper();

    public byte[] getAgenda(int meetingId) throws Exception {
        JsonNode meeting = meetingJsonDao.getMeetingJson(meetingId);
        String html=velocityUtil.transform((ObjectNode) mapper.readTree(mapper.writeValueAsString(meeting)),"classpath:template/agenda_pdf.vm");
        Document document = new Document(PageSize.LEGAL, 10, 10,10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("agenda.pdf"));
        document.open();
        InputStream is = new ByteArrayInputStream( html.getBytes() );
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                is);
        document.close();
        System.out.println( "PDF Created!" );

        RandomAccessFile f = new RandomAccessFile("agenda.pdf", "r");
        byte[] b = new byte[(int)f.length()];
        f.readFully(b);
        return b;
    }
}
