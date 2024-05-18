package com.loc.newsapp.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.R
import com.loc.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }

    Box(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .calendarBarBorder(),
            value = text,
            onValueChange = { newValue ->
                val formattedDate = formatInputDate(newValue)
                onValueChange(formattedDate)
            },
            readOnly = readOnly,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = colorResource(id = R.color.body)
                )
            },
            placeholder = {
                Text(
                    text = "DD/MM/YYYY",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.input_background),
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            visualTransformation = DateVisualTransformation()
        )
    }
}

fun formatInputDate(input: String): String {
    val unformatted = input.replace("[^\\d]".toRegex(), "")
    return buildString {
        if (unformatted.length >= 8) {
            append(unformatted.substring(0, 2))
            append("/")
            append(unformatted.substring(2, 4))
            append("/")
            append(unformatted.substring(4, 8))
        } else {
            append(unformatted)
        }
    }
}


fun Modifier.calendarBarBorder() = composed {
    if (!isSystemInDarkTheme()) {
        border(
            color = Color.Transparent, width = 1.dp, shape = MaterialTheme.shapes.extraLarge
        )
    } else {
        this
    }
}


class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var input = text.text
        if (input.length <= 2) {
            val formattedText = if (input.length == 2 && input.toInt() <= 31) "$input/" else input
            return TransformedText(
                AnnotatedString(formattedText),
                DateOffsetMapping()
            )
        }
        if (input.length <= 5) {
            val formattedText = if (input.length == 5 && input[3] == '/' && input.substring(3, 5)
                    .toInt() <= 12
            ) "$input/" else input
            return TransformedText(
                AnnotatedString(formattedText),
                DateOffsetMapping()
            )
        }
        return TransformedText(
            AnnotatedString(input),
            DateOffsetMapping()
        )
    }
}

class DateOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return offset
    }

    override fun transformedToOriginal(offset: Int): Int {
        return offset
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarBarPreview() {
    NewsAppTheme {
        CalendarBar(text = "", readOnly = false, onValueChange = {}, onSearch = {})
    }
}