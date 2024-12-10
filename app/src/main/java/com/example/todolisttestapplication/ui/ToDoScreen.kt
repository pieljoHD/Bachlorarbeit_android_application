package com.example.todolisttestapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.todolisttestapplication.R
import com.example.todolisttestapplication.beige
import com.example.todolisttestapplication.beige_hell
import com.example.todolisttestapplication.error_red
import com.example.todolisttestapplication.tuerkis_hell

@Composable
fun TodoScreen(
    navController: NavController
) {
    val todoList: MutableState<List<String>> = remember {
        mutableStateOf(listOf())
    }
    //save,todoString,index
    val popUpState = remember {
        mutableStateOf(Triple(false, "", -1))
    }

    if (popUpState.value.first) {
        ChangePopUp(popUpState, todoList)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 30.dp, vertical = 50.dp)
    ) {
        InputShit(todoList = todoList)

        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxSize()
                .background(beige_hell)
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .testTag("todoList")
        ) {
            todoList.value.forEachIndexed { index, todo ->
                item {
                    Row(modifier = Modifier.clickable {
                        popUpState.value = Triple(true, todo, index)
                    }) {
                        Text(
                            fontSize = 18.sp,
                            modifier = Modifier
                                .testTag("todoText $index")
                                .padding(bottom = 8.dp, start = 8.dp)
                                .fillMaxWidth(0.9f),
                            text = todo
                        )
                        Icon(
                            modifier = Modifier
                                .testTag("deleteButton $index")
                                .size(20.dp)
                                .clickable {
                                    val mutableList = todoList.value.toMutableList()
                                    mutableList.removeAt(index)
                                    todoList.value = mutableList
                                },
                            painter = painterResource(
                                id = R.drawable.icon_trash
                            ),
                            contentDescription = "",
                            tint = error_red
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(tuerkis_hell)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChangePopUp(popUpState: MutableState<Triple<Boolean, String, Int>>, todoList: MutableState<List<String>>) {
    Dialog(
        onDismissRequest = { popUpState.value = Triple(false, "", -1) }
    ) {
        Card(
            modifier = Modifier
                //because Dialog opens a new Frame
                .semantics { testTagsAsResourceId = true }
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = beige_hell)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                TextField(
                    modifier = Modifier
                        .testTag("changeTodoInput")
                        .fillMaxWidth(),
                    value = popUpState.value.second,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    onValueChange = {
                        popUpState.value = Triple(true, it, popUpState.value.third)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = beige,
                        unfocusedContainerColor = beige,
                        focusedIndicatorColor = beige,
                        unfocusedIndicatorColor = beige
                    ),
                    trailingIcon = {
                        if(popUpState.value.second.isNotEmpty()) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "clear text",
                                modifier = Modifier
                                    .testTag("clearButton")
                                    .clickable {
                                        popUpState.value = Triple(true, "", popUpState.value.third)
                                    }
                            )
                        }
                    },
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = Modifier
                            .testTag("abbrechen")
                            .height(28.dp)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = error_red),
                        contentPadding = PaddingValues(
                            start = 6.dp,
                            end = 6.dp
                        ),
                        onClick = {
                            popUpState.value = Triple(false, "", -1)
                        },
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "abbrechen",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .testTag("speichern")
                            .height(28.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = tuerkis_hell),
                        contentPadding = PaddingValues(
                            start = 6.dp,
                            end = 6.dp
                        ),
                        onClick = {
                            if (popUpState.value.second.isNotEmpty()) {
                                val todoListTmp = todoList.value.toMutableList()
                                todoListTmp[popUpState.value.third] = popUpState.value.second
                                todoList.value = todoListTmp
                                popUpState.value = Triple(false, "", -1)
                            }
                        },
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "speichern",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InputShit(todoList: MutableState<List<String>>) {
    val input = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(60.dp))
            .background(Color.Gray),
    ) {
        TextField(
            modifier = Modifier
                .testTag("todoInput")
                .fillMaxHeight()
                .fillMaxWidth(0.75f),
            value = input.value,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            onValueChange = {
                input.value = it
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = beige_hell,
                unfocusedContainerColor = beige_hell,
                focusedIndicatorColor = beige_hell,
                unfocusedIndicatorColor = beige_hell
            ),
            trailingIcon = {
                if(input.value.isNotEmpty()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .testTag("clearButton")
                            .clickable {
                                input.value = ""
                            }
                    )
                }
            },
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 0.dp,
                bottomStart = 50.dp,
                bottomEnd = 0.dp
            )
        )
        Button(
            modifier = Modifier
                .testTag("addButton")
                .fillMaxSize(),
            onClick = {
                if (input.value.isNotEmpty()) {
                    val mutableList = todoList.value.toMutableList()
                    mutableList.add(input.value)
                    todoList.value = mutableList.toList()
                    input.value = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = tuerkis_hell
            ),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 50.dp
            )
        ) {
            Text(
                fontSize = 20.sp,
                text = "ADD"
            )
        }
    }
}