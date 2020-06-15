package com.adullam.controller;

import com.adullam.domain.Cart;
import com.adullam.domain.Item;
import com.adullam.domain.Orders;
import com.adullam.form.CartDTO;
import com.adullam.service.CartService;
import com.adullam.service.ItemService;
import com.adullam.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final CartService cartService;
    private final MemberService memberService;
    private final ItemService itemService;
    
    //주문화면으로가기
    @GetMapping("/order")
    public String createForm(Model model, HttpSession session){
        log.info("ORDER : GET");
        String mb_id = (String)session.getAttribute("mb_id");
        /** if(mb_id == null) {로그인 해주시기 바랍니다.} **/

        List<Item> items = itemService.findItems();
        List<CartDTO> cartlist = cartService.findCartDTO(mb_id);

        model.addAttribute("items",items);
        model.addAttribute("cartlist", cartlist);

        return "order/orderForm";
    }

    //주문서 작성하기(주문)
    @PostMapping("/order")
    public String Order(HttpSession session){
        log.info("ORDER : POST");
        return "home";
    }
    
    //카드에 담기
    @PostMapping("/addcart")
    public String addCart(HttpSession session, @RequestParam("item_no") int item_no, @RequestParam("count") int count, Model model){
        log.info("ADDCART : POST");
        cartService.addCart((String)session.getAttribute("mb_id"), item_no, count);
        return "redirect:/order";
    }

    //카드 상품 삭제
    @PostMapping("/deletecart/{cart_no}")
    public String deleteCart(@PathVariable("cart_no") int cart_no){
        log.info("DELETECART : POST");
        cartService.deleteCart(cart_no);
        return "redirect:/order";
    }
}
