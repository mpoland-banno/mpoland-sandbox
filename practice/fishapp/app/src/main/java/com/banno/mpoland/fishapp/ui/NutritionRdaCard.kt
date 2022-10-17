package com.banno.mpoland.fishapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.banno.mpoland.fishapp.model.SpeciesNutritionalDetails

class NutritionRdaCardPreviewParameter: PreviewParameterProvider<SpeciesNutritionalDetails> {
    override val values: Sequence<SpeciesNutritionalDetails> = sequenceOf(
        SpeciesNutritionalDetails(
            calories = "110",
            carbohydrate = "0",
            cholesterol = "32 mg",
            totalFat = "2.29 g",
            totalDietaryFiber = "0",
            protein = "20.81 g",
            totalSaturatedFattyAcids = "0.325 g",
            selenium = "36.5 mcg",
            servingWeight = "100 g (raw)",
            servings = "1",
            sodium = "54 mg",
            totalSugars = "0"
        )
    )
}

@Preview
@Composable
fun NutritionRdaCard(@PreviewParameter(NutritionRdaCardPreviewParameter::class) nutritionalDetails: SpeciesNutritionalDetails) {

    Column(modifier = Modifier
        .background(color = Color.White)
        .border(1.dp, Color.Black)
        .padding(4.dp)
    ) {

        Text(
            text = "Nutrition Facts",
            fontWeight = FontWeight.Black,
            fontSize = 22.sp
        )

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=4.dp, bottom=4.dp))

        Row {
            Text(text = "Serving Size", fontSize = 12.sp)
            Text(
                text=nutritionalDetails.servingWeight ?: "",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }

        Divider(color = Color.Black, thickness = 4.dp, modifier = Modifier.padding(top=4.dp, bottom=4.dp))
        NutritionDataRow("Calories", nutritionalDetails.calories, boldLabel = true)

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp))
        NutritionDataRow("Total Fat", nutritionalDetails.totalFat, boldLabel = true)
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp, start=10.dp))
        NutritionDataRow("Saturated Fatty Acids", nutritionalDetails.totalFat, indent = true)

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp))
        NutritionDataRow("Cholesterol", nutritionalDetails.cholesterol, boldLabel = true)

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp))
        NutritionDataRow("Sodium", nutritionalDetails.sodium, boldLabel = true)

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp))
        NutritionDataRow("Total Carbohydrate", nutritionalDetails.carbohydrate, boldLabel = true)
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp, start=10.dp))
        NutritionDataRow("Total Sugars", nutritionalDetails.totalSugars, indent = true)

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp))
        NutritionDataRow("Protein", nutritionalDetails.protein, boldLabel = true)

        Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(top=2.dp, bottom=2.dp))
        NutritionDataRow("Selenium", nutritionalDetails.selenium, boldLabel = true)
    }

}


@Composable
fun NutritionDataRow(label:String, value:String?, boldLabel:Boolean=false, indent:Boolean=false) {
    Row {
        Text(
            text=label,
            fontSize = 12.sp,
            fontWeight = if (boldLabel) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(
                start = if (indent) 10.dp else 0.dp,
                end = 5.dp
            )
        )

        Text(text=value ?: "N/A", fontSize = 12.sp)
    }
}
