package com.lacunasaurus.tutorial.web.controllers.admin;

import com.lacunasaurus.tutorial.web.configuration.ApplicationInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin"})
public class AdminUserAccountController {

    @Autowired
    private ApplicationInformation applicationInformation;
    
    @ModelAttribute("accountList")
    public void setDefaultAdminData(Model model) {
        model.addAttribute("accountList", applicationInformation.getUserAccountList().values());
    }
    
    @GetMapping({"/liste-des-utilisateurs", "/user-accounts"})
    public String navigate() {
        return "pages/admin/user-accounts";
    }
}
