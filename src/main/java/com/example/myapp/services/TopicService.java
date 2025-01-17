package com.example.myapp.services;

import com.example.myapp.models.Topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.myapp.models.Widget;
import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    WidgetRepository widgetRepository;

    public List<Topic> findAllTopics() {
        return (List<Topic>)topicRepository.findAll();
    }

    public Topic findTopicById(Integer topicId) {
        return topicRepository.findTopicById(topicId);
    }

    public int deleteTopic(Integer topicId) {
        topicRepository.deleteById(topicId);
        return 1;
    }

    public int updateTopic(Integer topicId, Topic updatedTopic) {

        Topic oldTopic = topicRepository.findTopicById(topicId);
        oldTopic.setTitle(updatedTopic.getTitle());
        oldTopic.setDescription(updatedTopic.getDescription());
        oldTopic.setWidgets(updatedTopic.getWidgets());
        topicRepository.save(oldTopic);
        return 1;
    }

    public Topic createTopic(String lessonId, Topic topic) {
        topic.setLessonId(lessonId);
        return topicRepository.save(topic);
    }

    public Widget createWidgetForTopic(
            Integer tid,
            Widget newWidget) {
        Topic topic = topicRepository.findById(tid).get();
        newWidget.setTopic(topic);
        newWidget.setwidgetOrder(widgetRepository.findWidgetsForTopic(tid).size());
        return widgetRepository.save(newWidget);
    }

    public List<Topic> findTopicsForLesson(String lessonId) {
        return topicRepository.findTopicsForLesson(lessonId);
    }
}

