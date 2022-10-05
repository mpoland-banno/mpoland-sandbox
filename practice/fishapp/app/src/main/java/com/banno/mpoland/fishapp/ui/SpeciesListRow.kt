package com.banno.mpoland.fishapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.banno.mpoland.fishapp.model.SpeciesImageData
import com.banno.mpoland.fishapp.model.SpeciesSlim


typealias OnClickSpeciesRowFunc = (SpeciesSlim)->Unit

class SpeciesListRowPreviewParameter : PreviewParameterProvider<SpeciesSlim> {
    override val values: Sequence<SpeciesSlim> = sequenceOf(
        SpeciesSlim("Fish!", "Feeeeeeesh!!", "", SpeciesImageData.DefaultImageData)
    )
}

@Preview
@Composable
fun SpeciesListRow(
    @PreviewParameter(SpeciesListRowPreviewParameter::class) species: SpeciesSlim,
    onClickSpeciesRow:OnClickSpeciesRowFunc = {}
) {
    Row(
        Modifier
            .padding(10.dp)
            .clickable { onClickSpeciesRow(species) }
    ) {

        AsyncImage(
            model = species.speciesIllustrationPhoto.src,
            contentDescription = species.speciesIllustrationPhoto.title,
            modifier = Modifier
                .width(100.dp)
                .defaultMinSize(minWidth = 100.dp)
                .padding(end = 10.dp)
                .align(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = species.speciesName)
            Text(text = species.scientificName,
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray,
            )
        }
    }
}