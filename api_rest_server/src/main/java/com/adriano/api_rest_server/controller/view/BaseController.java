package com.adriano.api_rest_server.controller.view;

import com.adriano.api_rest_server.domain.entity.BaseEntity;
import com.adriano.api_rest_server.service.BaseService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<T extends BaseEntity, ID> {

    protected final BaseService<T, ID> baseService;
    protected final String entityName;
    protected final String basePath;

    public BaseController(BaseService<T, ID> baseService, String entityName, String entityNamePlural) {
        this.baseService = baseService;
        this.entityName = entityName;
        this.basePath = entityNamePlural.toLowerCase();
    }

    @GetMapping
    public String listAll(Model model) {
        List<T> entities = baseService.findAll();
        model.addAttribute("entities", entities);
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        return basePath + "/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable ID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<T> entity = baseService.findById(id);
        if (entity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        model.addAttribute("entity", entity.get());
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        return basePath + "/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("entity", createNewInstance());
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        model.addAttribute("isEdit", false);
        return basePath + "/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable ID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<T> entity = baseService.findById(id);
        if (entity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        model.addAttribute("entity", entity.get());
        model.addAttribute("entityName", entityName);
        model.addAttribute("basePath", basePath);
        model.addAttribute("isEdit", true);
        return basePath + "/form";
    }

    @PostMapping
    public String create(@ModelAttribute T entity, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", entityName + " created successfully");
        baseService.save(entity);
        return "redirect:/" + basePath;
    }

    @PostMapping("/{id}")
    public String update(@PathVariable ID id, @ModelAttribute T entity, RedirectAttributes redirectAttributes) {
        T updated = baseService.update(id, entity);
        if (updated == null) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        redirectAttributes.addFlashAttribute("success", entityName + " updated successfully");
        return "redirect:/" + basePath;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ID id, RedirectAttributes redirectAttributes) {
        Optional<T> existing = baseService.findById(id);
        if (existing.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", entityName + " not found");
            return "redirect:/" + basePath;
        }
        baseService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", entityName + " deleted successfully");
        return "redirect:/" + basePath;
    }

    protected abstract T createNewInstance();
}
