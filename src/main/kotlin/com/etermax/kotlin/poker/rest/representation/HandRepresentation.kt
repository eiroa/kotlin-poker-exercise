package com.etermax.kotlin.poker.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class HandRepresentation(@JsonProperty("hand") val cards: List<String>)