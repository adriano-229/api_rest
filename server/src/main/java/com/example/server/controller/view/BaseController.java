package com.example.server.controller.view;

import com.example.server.domain.entity.BaseEntity;
import com.example.server.service.BaseService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<T extends BaseEntity, ID> {

    protected final BaseService<E, D, ID> baseService;
    protected final String entityName;
    protected final String basePath;

    public BaseController(BaseService<T, ID> baseService, String entityName, String entityNamePlural) {
        this.baseService = baseService;
        this.entityName = entityName;
        this.basePath = entityNamePlural.toLowerCase();
    }


    @GetMapping
    public String listAll(Model model) {
        List<T> entities = baseService.readAll();
        model.addAttribute("entities", entities);
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        return template("list");
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") ID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<T> entity = baseService.readById(id);
        if (entity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        model.addAttribute("entity", entity.get());
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        model.addAttribute("isReadOnly", true);
        model.addAttribute("isEdit", false);
        return template("form");
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("entity", createNewInstance());
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        model.addAttribute("isReadOnly", false);
        model.addAttribute("isEdit", false);
        return template("form");
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") ID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<T> entity = baseService.readById(id);
        if (entity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        model.addAttribute("entity", entity.get());
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        model.addAttribute("isReadOnly", false);
        model.addAttribute("isEdit", true);
        return template("form");
    }

    @PostMapping
    public String create(@ModelAttribute T entity, RedirectAttributes redirectAttributes) {
        try {
            baseService.create(entity);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/" + template("new");
        }
        redirectAttributes.addFlashAttribute("success", entityName + " created successfully");
        return "redirect:/" + basePath;
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") ID id, @ModelAttribute T entity, RedirectAttributes redirectAttributes) {
        T updated = baseService.update(id, entity);
        if (updated == null) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        redirectAttributes.addFlashAttribute("success", entityName + " updated successfully");
        return "redirect:/" + basePath;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") ID id, RedirectAttributes redirectAttributes) {
        Optional<T> existing = baseService.readById(id);
        if (existing.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        baseService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", entityName + " deleted successfully");
        return "redirect:/" + basePath;
    }

    protected abstract T createNewInstance();

    protected String template(String viewName) {
        return basePath + "/" + viewName;
    }

}
