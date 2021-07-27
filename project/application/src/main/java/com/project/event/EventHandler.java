package com.project.event;

import com.project.diet.service.ReadExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final ReadExcelService readExcelService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String type;

    @Order(1)
    @EventListener
    public void  applicationStartEventListener(ApplicationStartedEvent event) throws IOException {
        if (type.equals("create")) {
            InputStream foodExcel = new ClassPathResource("food.xlsx").getInputStream();
            readExcelService.inputFood(foodExcel);
        }

    }
}
