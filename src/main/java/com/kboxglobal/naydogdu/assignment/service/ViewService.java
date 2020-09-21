package com.kboxglobal.naydogdu.assignment.service;

import com.kboxglobal.naydogdu.assignment.entity.User;
import com.kboxglobal.naydogdu.assignment.entity.View;
import com.kboxglobal.naydogdu.assignment.exception.UserNotFoundException;
import com.kboxglobal.naydogdu.assignment.exception.ViewNotFoundException;
import com.kboxglobal.naydogdu.assignment.paging.CursorPager;
import com.kboxglobal.naydogdu.assignment.repository.UserRepository;
import com.kboxglobal.naydogdu.assignment.repository.ViewRepository;
import com.kboxglobal.naydogdu.assignment.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class ViewService {
    private final ViewRepository viewRepository;
    private final UserRepository userRepository;

    public ViewService(ViewRepository viewRepository, UserRepository userRepository) {
        this.viewRepository = viewRepository;
        this.userRepository = userRepository;
    }

    public CursorPager<View> getAllViews(String next) {
        List<View> views;
        Date dateBefore = DateUtil.currentDate();
        if (!StringUtils.isEmpty(next)) {//if next is not empty then user wants to go to next page, if empty then it is the first page
            dateBefore = DateUtil.stringToDate(next);
        }
        views = viewRepository.findTop20ByDateBeforeOrderByDateDesc(dateBefore);
        return getViewCursorPager(views);
    }

    public CursorPager<View> getViewsByVieweeId(Long vieweeId, String next) {
        List<View> views;
        Date dateBefore = DateUtil.currentDate();
        if (!StringUtils.isEmpty(next)) {//if next is not empty then user wants to go to next page, if empty then it is the first page
            dateBefore = DateUtil.stringToDate(next);
        }
        views = viewRepository.findViews(vieweeId, dateBefore);
        return getViewCursorPager(views);
    }

    public View addView(Long viewerId, Long vieweeId) {
        User viewer = userRepository.findById(viewerId).orElseThrow(() -> new UserNotFoundException(viewerId));
        User viewee = userRepository.findById(vieweeId).orElseThrow(() -> new UserNotFoundException(vieweeId));
        View view = new View(viewer, viewee);
        return viewRepository.save(view);
    }

    public void removeViewById(Long viewId) {
        View view = viewRepository.findById(viewId).orElseThrow(() -> new ViewNotFoundException(viewId));
        viewRepository.delete(view);
    }

    private CursorPager<View> getViewCursorPager(List<View> views) {
        String nextCursor;
        CursorPager<View> cursorPager;
        if (views.size() >= 1) {
            nextCursor = String.valueOf(views.get(views.size() - 1).getDate().getTime());
            cursorPager = new CursorPager<View>(views, 20, nextCursor, views.size());
            return cursorPager;
        }
        return null;
    }
}
