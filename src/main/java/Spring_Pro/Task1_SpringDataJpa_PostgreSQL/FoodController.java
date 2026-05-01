package Spring_Pro.Task1_SpringDataJpa_PostgreSQL;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FoodController {

    private final FoodService foodService;
    private final ManufacturerRepository manufacturerRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("foods", foodService.getAllFoods());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Food food = foodService.getFoodById(id);

        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturerList);
        model.addAttribute("food", food);
        return "addFood";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("food", new Food());

        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();

        System.out.println("DEBUG: Найдено производителей в БД: " + manufacturerList.size());

        model.addAttribute("manufacturers", manufacturerList);
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