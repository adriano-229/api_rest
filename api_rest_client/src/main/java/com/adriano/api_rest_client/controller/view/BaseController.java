package com.adriano.api_rest_client.controller.view;

import com.adriano.api_rest_client.domain.dto.BaseDTO;
import com.adriano.api_rest_client.service.CrudClientService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

public abstract class BaseController<T extends BaseDTO, ID> {

    protected final String entityName;
    protected final String basePath;
    private final CrudClientService<T, ID> service;

    protected BaseController(CrudClientService<T, ID> service, String entityName, String basePath) {
        this.service = service;
        this.entityName = entityName;
        this.basePath = basePath;
    }

    protected abstract T createNewInstance();

    protected void customizeListModel(Model model) {
    }

    protected void customizeFormModel(Model model, T entity) {
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("entities", service.findAll());
        addCommon(model);
        customizeListModel(model);
        return template("list");
    }

    @GetMapping("/{id}")
    public String view(@PathVariable ID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<T> entity = service.findById(id);
        if (entity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return redirectToList();
        }
        return renderForm(model, entity.get(), false, true);
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        return renderForm(model, createNewInstance(), false, false);
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable ID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<T> entity = service.findById(id);
        if (entity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return redirectToList();
        }
        return renderForm(model, entity.get(), true, false);
    }

    @PostMapping
    public String create(@ModelAttribute T entity, RedirectAttributes redirectAttributes) {
        service.create(entity);
        redirectAttributes.addFlashAttribute("success", entityName + " created");
        return redirectToList();
    }

    @PostMapping("/{id}")
    public String update(@PathVariable ID id, @ModelAttribute T entity, RedirectAttributes redirectAttributes) {
        service.update(id, entity);
        redirectAttributes.addFlashAttribute("success", entityName + " updated");
        return redirectToList();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ID id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("success", entityName + " deleted");
        return redirectToList();
    }

    private String renderForm(Model model, T entity, boolean isEdit, boolean isReadOnly) {
        model.addAttribute("entity", entity);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("isReadOnly", isReadOnly);
        addCommon(model);
        customizeFormModel(model, entity);
        return template("form");
    }

    private void addCommon(Model model) {
        model.addAttribute("basePath", basePath);
        model.addAttribute("entityName", entityName);
    }

    protected String template(String viewName) {
        return basePath + "/" + viewName;
    }

    private String redirectToList() {
        return "redirect:/" + basePath;
    }
}
