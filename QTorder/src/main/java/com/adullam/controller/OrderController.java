package com.adullam.controller;

import com.adullam.domain.Cart;
import com.adullam.domain.Item;
import com.adullam.form.CartDTO;
import com.adullam.service.CartService;
import com.adullam.service.ItemService;
import com.adullam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Item> items = itemService.findItems();
        List<CartDTO> cartlist = cartService.findCart();

        model.addAttribute("items",items);
        model.addAttribute("cartlist", cartlist);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(HttpSession session, @RequestParam("item_no") int item_no, @RequestParam("count") int count, Model model){
        System.out.println("---------- HTTPSESSION TEST : ORDER ----------");
        System.out.println(session.getAttribute("mb_id") + " " + item_no + " " + count);
        System.out.println("---------- HTTPSESSION TEST : ORDER ----------");
        cartService.addCart((String)session.getAttribute("mb_id"), item_no, count);

        return "redirect:/order";
    }
}
