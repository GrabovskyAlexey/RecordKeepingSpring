package ru.grabovky.recordkeeping.frontend

import kotlinx.browser.document
import react.Fragment
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.h1
import web.dom.Element

//import react.dom.client.createRoot

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 03.01.2024
 */

fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find root container!")
    createRoot(container.unsafeCast<Element>()).render(Fragment.create {
        h1 {
            +"Hello, React+Kotlin/JS!"
        }
    })
}