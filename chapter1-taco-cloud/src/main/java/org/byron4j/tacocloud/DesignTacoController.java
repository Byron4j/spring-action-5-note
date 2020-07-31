package org.byron4j.tacocloud;


import lombok.extern.slf4j.Slf4j;
import org.byron4j.tacocloud.domain.Ingredient;
import org.byron4j.tacocloud.domain.Taco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){

        List<Ingredient> ingredients = Arrays.asList(
          new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
          new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.PROTEIN),
          new Ingredient("CHEO", "Cheddar", Ingredient.Type.WRAP),
          new Ingredient("SRCR", "Sourc Cream", Ingredient.Type.SAUCE)
        );

        // 按种类获取列表
        Ingredient.Type[] values = Ingredient.Type.values();
        for (Ingredient.Type type : values) {
            // 根据type找到该type的列表
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {

       return ingredients.stream()
            .filter(ele -> ele.getType().equals(type))
            .collect(Collectors.toList());
    }



    // 参数使用 design 绑定，因为design页面的是design
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("处理设计...." + design);
        // 重定向到订单控制器
        return "redirect:/orders/current";
    }
}
