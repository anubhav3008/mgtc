package com.anubhav.mgtc.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@Service
public class VelocityUtil {

    TimeUtil timeUtil= new TimeUtil();
    public String transform(ObjectNode data, String file){
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        Template t = velocityEngine.getTemplate(file);
        VelocityContext context = new VelocityContext();
        context.put("data",data);
        context.put("timeUtil",timeUtil );
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        return writer.toString();
    }
}
