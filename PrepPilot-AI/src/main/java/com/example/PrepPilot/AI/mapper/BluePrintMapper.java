package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.dto.BluePrintSectionResponse;
import com.example.PrepPilot.AI.dto.BluePrintTopicResponse;
import com.example.PrepPilot.AI.entity.BluePrintSection;
import com.example.PrepPilot.AI.entity.BluePrintTopic;
import com.example.PrepPilot.AI.entity.InterviewBluePrint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BluePrintMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "resumeId", ignore = true)
    @Mapping(target = "jd_id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "sections", ignore = true)
    InterviewBluePrint toBluePrintWithoutSections(BluePrintResponse response);


    default InterviewBluePrint toBluePrint(BluePrintResponse response) {

        InterviewBluePrint bluePrint =
                toBluePrintWithoutSections(response);

        bluePrint.setTotalQuestions(response.totalQuestions());

        if (response.sections() != null) {

            for (BluePrintSectionResponse sectionResponse
                    : response.sections()) {

                BluePrintSection section = new BluePrintSection();

                section.setName(sectionResponse.name());
                section.setSequence(sectionResponse.sequence());
                section.setWeightage(sectionResponse.weightage());

                // IMPORTANT:
                // BluePrintSection owns the relationship
                section.setBluePrint(bluePrint);

                if (sectionResponse.topics() != null) {

                    for (BluePrintTopicResponse topicResponse
                            : sectionResponse.topics()) {

                        BluePrintTopic topic = new BluePrintTopic();

                        topic.setName(topicResponse.name());
                        topic.setDifficulty(topicResponse.difficulty());
                        topic.setWeightage(topicResponse.weightage());
                        topic.setPriority(topicResponse.priority());

                        // IMPORTANT:
                        // BluePrintTopic owns the relationship
                        topic.setSection(section);

                        section.getTopics().add(topic);
                    }
                }

                bluePrint.getSections().add(section);
            }
        }

        return bluePrint;
    }
}

