package com.banno.mpoland.fishapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
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
import com.banno.mpoland.fishapp.model.Species
import com.banno.mpoland.fishapp.model.SpeciesImageData
import com.banno.mpoland.fishapp.viewmodel.SpeciesListUiStateHolder


@Composable
fun SpeciesList(speciesList:List<Species>, onClickSpeciesRow: (Species) -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(items=speciesList) { index, item ->
            SpeciesListRow(item, onClickSpeciesRow)
            if (index < speciesList.size) {
                Divider(thickness = 1.dp)
            }
        }
    }
}

class SpeciesListRowPreviewParameter : PreviewParameterProvider<Species> {
    override val values: Sequence<Species> = sequenceOf(
        Species("Fish!", "Feeeeeeesh!!", "", SpeciesImageData.DefaultImageData)
    )
}

@Preview
@Composable
fun SpeciesListRow(
    @PreviewParameter(SpeciesListRowPreviewParameter::class) species: Species,
    onClickSpeciesRow: (Species)->Unit = {}
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