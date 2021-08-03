package com.project.diet.service;

import com.monitorjbl.xlsx.StreamingReader;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ReadExcelService {

    private final FoodRepository foodRepository;


    @Transactional
    public void inputFood(InputStream inputStream) {
        Workbook workBook = StreamingReader.builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .open(inputStream);

        Sheet workSheet = workBook.getSheetAt(0);
        for (Row row : workSheet) {
            Long id = (long) row.getCell(0).getNumericCellValue();

            String name = row.getCell(1).getStringCellValue();

            String foodCategory = row.getCell(2).getStringCellValue();

            int size = (int) getNumericCell(row, 3);

            String unit = row.getCell(4).getStringCellValue();

            double protein = getNumericCell(row, 5);

            double fat = getNumericCell(row, 6);

            double carbohydrate = getNumericCell(row, 7);

            double calories = getNumericCell(row, 8);

            Ingredient ingredient = new Ingredient(protein,
                    fat,
                    carbohydrate,
                    calories);
            foodRepository.save(new Food(
                    id,
                    name,
                    foodCategory,
                    size,
                    unit,
                    ingredient
            ));
        }
    }

    private double getNumericCell(Row row, int idx) {
        try {
            return row.getCell(idx).getNumericCellValue();
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

