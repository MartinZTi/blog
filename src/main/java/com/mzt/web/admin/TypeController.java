package com.mzt.web.admin;

import com.mzt.pojo.Type;
import com.mzt.service.TypeService;
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
 * @Date : 2019/8/26 14:09
 * @Description : com.mzt.web.admin
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                        Model model) {
        //根据前端页面构造好的参数,自动封装到pageable对象中,以实现分页的方法
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";

        /*page对象中封装的数据:
        content: json格式的数据数组列表
        last : false    是否是最后一页
        totalPages:     总页数
        totalElements:  总记录数
        size :          每页记录条数
        number:         当前页
        first:          是否为第一页
        sort    :       排序的设置
                --direction: 升序降序
                --property:  根据什么属性来排序
                --ignoreCase:
                --nullHandling:
                --ascending:
         numberOfElements: 当前页记录条数

        */
    }

    @RequestMapping("/types/input")
    public String input(Model model) {
        //这里本来只是响应页面,没有参数
        //传递一个type对象,只是为了校验,满足前端需要此对象的需求, 否则前端页面报错
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    //分类添加  (form表单 post提交)
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        //同名类型不得入库校验
        if (type1 != null){
            result.rejectValue("name","nameError","已存在, 不能添加重复的分类");
        }

            if (result.hasErrors()) {
                return "admin/types-input";
            }

        Type t = typeService.saveType(type);
        if (t == null) {
            //没成功
            attributes.addFlashAttribute("message", "新增失败!");
        } else {
            attributes.addFlashAttribute("message", "新增成功!");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/edit")
    //修改type (先查再改)
    public String editInput(@PathVariable Long id , Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //修改后提交  (form表单 post提交)
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        //同名类型不得入库校验
        if (type1 != null){
            result.rejectValue("name","nameError","已存在, 不能添加重复的分类");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.updateType(id,type);
        if (t == null) {
            //没成功
            attributes.addFlashAttribute("message", "更新失败!");
        } else {
            attributes.addFlashAttribute("message", "更新成功!");
        }
        return "redirect:/admin/types";
    }

    //删除 delete
//    @{/admin/types/{id}/delete(id=${type.id})}
    @RequestMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


}
