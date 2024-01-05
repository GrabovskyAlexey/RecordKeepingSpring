package ru.grabovsky.recordkeeping.core.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 30.12.2023
 */
@Controller
class DocsController {
    @RequestMapping("/")
    fun index() = "redirect:swagger-ui.html"
}