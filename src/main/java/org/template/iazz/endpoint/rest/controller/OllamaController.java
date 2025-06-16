package org.template.iazz.endpoint.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.template.iazz.service.OllamaService;

@RestController
@RequestMapping("/api/chat")
public class OllamaController {

  private final org.template.iazz.service.OllamaService ollamaService;

  public OllamaController(OllamaService ollamaService) {
    this.ollamaService = ollamaService;
  }

  @PostMapping
  public String chat(@RequestBody String prompt) {
    return ollamaService.askDeepseekR1(prompt);
  }
}
