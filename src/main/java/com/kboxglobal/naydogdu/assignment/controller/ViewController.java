package com.kboxglobal.naydogdu.assignment.controller;

import com.kboxglobal.naydogdu.assignment.entity.View;
import com.kboxglobal.naydogdu.assignment.paging.CursorPager;
import com.kboxglobal.naydogdu.assignment.service.ViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class ViewController {
    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/views")
    public ResponseEntity<CursorPager<View>> getProfileViews(@RequestParam(value = "next", defaultValue = "", required = false) String next) throws ParseException {
        return ResponseEntity.ok(viewService.getAllViews(next));
    }

    @GetMapping("/views/{vieweeId}")
    public CursorPager<View> getProfileView(@PathVariable Long vieweeId,
                                          @RequestParam(value = "next", defaultValue = "", required = false) String next) throws ParseException {
        return viewService.getViewsByVieweeId(vieweeId, next);
    }

    @PostMapping("/views")
    public View addProfileView(@PathParam("viewerId") Long viewerId, @PathParam("vieweeId") Long vieweeId){
        return viewService.addView(viewerId, vieweeId);
    }

    @DeleteMapping("/views/{id}")
    public ResponseEntity deleteProfileView(@PathVariable Long id) {
        viewService.removeViewById(id);
        return ResponseEntity.noContent().build();
    }
}
