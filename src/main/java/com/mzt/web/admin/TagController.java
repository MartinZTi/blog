package com.mzt.web.admin;

import com.mzt.pojo.Tag;
import com.mzt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/27 12:45
 * @Description : com.mzt.web.admin
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //分页查询
    //    /admin/tags
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10 ,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                       Model model){
        model.addAttribute("page", tagService.listTag(pageable));

        return "admin/tags";
    }

    //跳转 新增/修改 页面  @{/admin/tags/input}  共用了一张HTML
    @RequestMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    //新增操作 form-post提交  /admin/tags
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result,
                       RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        //同名校验
        if(t !=null){
            result.rejectValue("name","nameError","已存在, 不能添加重复的标签");
        }

        if(result.hasErrors()){
            return "admin/tags-input";
        }

        Tag tagFromDB = tagService.saveTag(tag);
        if(tagFromDB == null){
            attributes.addFlashAttribute("message","新增失败!");
        }else {
            attributes.addFlashAttribute("message","新增成功!");
        }
        return "redirect:/admin/tags";
    }

    //修改操作
    // ----1查
    //@{/admin/tags/{id}/edit(id=${tag.id})}
    @GetMapping("/tags/{id}/edit")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    //-----2.改
    // @{/admin/tags/{id}(id=*{id})}
    @PostMapping("/tags/{id}")
    public String editPost(@PathVariable Long id,
                           @Valid Tag tag,BindingResult result,
                           RedirectAttributes attributes){
        //查重
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName != null){
            result.rejectValue("name","nameError","已存在, 不能添加重复的标签");
        }

        if(result.hasErrors()){
            return "admin/tags-input";
        }

        Tag updateTag = tagService.updateTag(id, tag);
        if(updateTag == null){
            attributes.addFlashAttribute("message","更新失败!");
        }else {
            attributes.addFlashAttribute("message","更新成功!");
        }
        return "redirect:/admin/tags";
    }

    //删除
    //@{/admin/tags/{id}/delete(id=${tag.id})}
    @RequestMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
