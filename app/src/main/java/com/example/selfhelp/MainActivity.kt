package com.example.selfhelp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.selfhelp.data.HelpData
import com.example.selfhelp.data.helps

import com.example.selfhelp.ui.theme.SelfHelpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfHelpTheme {
                HelpApp()
            }
        }
    }
}
//MAIN APP
@Composable
fun HelpApp() {
    Scaffold(
        topBar = {
            HelpTopAppBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(helps) {
                HelpItem(help = it)
            }
        }
    }
}

//TOP BAR
@Composable
fun HelpTopAppBar(modifier: Modifier = Modifier){
    var isExpanded by remember { mutableStateOf(false)}
    var con = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(64.dp)
                .padding(8.dp),
            painter = painterResource(R.drawable.images),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )

        Spacer(modifier=Modifier.weight(1f))
        IconButton(onClick = { Toast.makeText(con, "Thanks for the Like", Toast.LENGTH_SHORT).show() }) {
            Icon(Icons.Default.Favorite,"")
        }
        IconButton(onClick = { isExpanded=!isExpanded }) {
            Icon(Icons.Default.MoreVert,"")
        }
        Box (modifier = Modifier
            .padding(top = 64.dp, end = 8.dp))
        {
            Box {
                DropdownMenu(
                    expanded = (isExpanded),
                    modifier = Modifier

                        .wrapContentSize(Alignment.TopStart),
                    onDismissRequest = { isExpanded = false }) {
                    DropdownMenuItem(onClick = {
                        Toast.makeText(
                            con,
                            "Hello from Binary Brains",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text(text = "Developed By",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h3)
                    }
                    DropdownMenuItem(onClick = {
                        Toast.makeText(
                            con,
                            "Hiii from Priyankar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text(text = "Priyankar Koley")
                    }
                    DropdownMenuItem(onClick = {
                        Toast.makeText(
                            con,
                            "Hey from Santanu",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text(text = "Santanu Pal")
                    }
                    DropdownMenuItem(onClick = {
                        Toast.makeText(
                            con,
                            "Hola from Pranti",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text(text = "Pranti Rani Banda")
                    }
                    DropdownMenuItem(onClick = {
                        Toast.makeText(
                            con,
                            "Heyy from Debosmita",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text(text = "Debosmita Bedajna")
                    }
                }
            }
        }
    }
}

//EACH ITEM
@Composable
fun HelpItem(help: HelpData, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val ctx = LocalContext.current
    var helpNumber :String = stringResource(help.num)
    Card(
        elevation = 4.dp,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                expanded = !expanded
            }
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HelpIcon(help.imageResourceId)
                HelpName(help.name)
                Spacer(Modifier.weight(1f))
                HelpButton(
                    onClick = {callDialer(ctx, helpNumber)},
                )
                Spacer(Modifier.width(8.dp))
            }
            if (expanded) {
                HelpDetails(help.num)
            }
        }


    }
}

//ICON
@Composable
fun HelpIcon(@DrawableRes helpIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50))
            .background(color = colorResource(id = R.color.bg_col)),
        contentScale = ContentScale.Crop,
        painter = painterResource(helpIcon),
        contentDescription = null
    )
}

//BASIC INFO
@Composable
fun HelpName(@StringRes helpName: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(helpName),
            style = MaterialTheme.typography.h2,
            modifier = modifier.padding(8.dp)
        )
    }
}

//DETAILED INFO
@Composable
fun HelpDetails(@StringRes helpDetail: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(R.string.num),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(helpDetail),
            style = MaterialTheme.typography.body1
        )
    }
}

//BUTTON TO REDIRECT TO DIALER
@Composable
private fun HelpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick) {
        Image(
            painter = painterResource(id = R.drawable.phone),
            modifier = Modifier
                .size(32.dp),
            contentDescription = stringResource(R.string.call_button_description)
        )
    }
}


fun callDialer(ctx: Context, helpName: String){

    val u = Uri.parse("tel:$helpName")
    val i = Intent(Intent.ACTION_DIAL, u)
    try {
        ctx.startActivity(i)
    } catch (s: SecurityException) {
        Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
            .show()
    }

}




//------------------------------------------------------------------------
//PREVIEW
//------------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    SelfHelpTheme(darkTheme = false) {
        HelpApp()
    }
}
@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    SelfHelpTheme(darkTheme = true) {
        HelpApp()
    }
}