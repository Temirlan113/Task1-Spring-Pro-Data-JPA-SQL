package Spring_Pro.Task1_SpringDataJpa_PostgreSQL;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("foods", foodService.getAllFoods());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Food food = foodService.getFoodById(id);
        model.addAttribute("food", food);
        return "addFood";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("food", new Food());
        return "addFood";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute Food food) {
        foodService.addFood(food);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveFood(@ModelAttribute("food") Food food) {
        foodService.addFood(food);
        return "redirect:/";
    }
}