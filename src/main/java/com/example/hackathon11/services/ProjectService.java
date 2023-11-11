package com.example.hackathon11.services;

import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.converter.ProjectConverter;
import com.example.hackathon11.dto.*;
import com.example.hackathon11.entity.*;
import com.example.hackathon11.exceptions.InputDataErrorException;
import com.example.hackathon11.repository.*;
import com.example.hackathon11.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.hackathon11.entity.Fit.COVER;

@Service
@RequiredArgsConstructor
public class ProjectService implements CustomConstants {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final SimpleIconRepository simpleIconRepository;
    private final ImageIconRepository imageIconRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final TextRepository textRepository;
    private final ProjectRepository projectRepository;
    private final CanvasRepository canvasRepository;
    private final ProjectConverter projectConverter;

    public StringResponse save(ProjectDto projectDto, String token) throws InputDataErrorException {
        try {
            String email = jwtTokenUtil.getUsernameFromToken(token);

            User user = userRepository.findByUsername(email).orElseThrow
                    (() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));

            Project project = new Project();
            project.setName(projectDto.getName());
            projectRepository.save(project);

            List<ImageDto> imageDtos = projectDto.getImages();
            for (ImageDto imageDto : imageDtos) {
                Image image = new Image();
                String fitDto = imageDto.getFit();
                if ("contain".equals(fitDto)) {
                    image.setFit(Fit.CONTAIN);
                } else {
                    image.setFit(COVER);
                }
                image.setSrc(imageDto.getSrc());
                image.setWidth(imageDto.getWidth());
                image.setHeight(imageDto.getHeight());
                image.setProject(project);
                imageRepository.save(image);
            }

            List<SimpleIconDto> simpleIconDtos = projectDto.getSimpleIcons();
            for (SimpleIconDto simpleIconDto : simpleIconDtos) {
                SimpleIcon simpleIcon = new SimpleIcon();
                simpleIcon.setColor(simpleIconDto.getColor());
                Coordinates coordinates = new Coordinates();
                coordinates.setX(simpleIconDto.getCoordinates().getX());
                coordinates.setY(simpleIconDto.getCoordinates().getY());
                coordinatesRepository.save(coordinates);
                simpleIcon.setCoordinates(coordinates);
                simpleIcon.setId(simpleIconDto.getId());
                simpleIcon.setSize(simpleIconDto.getSize());
                simpleIcon.setRotate(simpleIconDto.getRotate());
                simpleIcon.setZIndex(simpleIconDto.getZIndex());
                simpleIcon.setProject(project);
                simpleIconRepository.save(simpleIcon);
            }

            List<ImageIconDto> imageIconDtos = projectDto.getImageIcons();
            for (ImageIconDto imageIconDto : imageIconDtos) {
                ImageIcon imageIcon = new ImageIcon();
                Coordinates coordinates = new Coordinates();
                coordinates.setX(imageIconDto.getCoordinates().getX());
                coordinates.setY(imageIconDto.getCoordinates().getY());
                coordinatesRepository.save(coordinates);
                imageIcon.setCoordinates(coordinates);
                imageIcon.setId(imageIconDto.getId());
                imageIcon.setSize(imageIconDto.getSize());
                imageIcon.setRotate(imageIconDto.getRotate());
                imageIcon.setZIndex(imageIconDto.getZIndex());
                imageIcon.setProject(project);
                imageIconRepository.save(imageIcon);
            }

            List<TextDto> textDtos = projectDto.getTexts();
            for (TextDto textDto : textDtos) {
                Text text = new Text();
                Coordinates coordinates = new Coordinates();
                coordinates.setX(textDto.getCoordinates().getX());
                coordinates.setY(textDto.getCoordinates().getY());
                coordinatesRepository.save(coordinates);
                text.setCoordinates(coordinates);
                text.setRotate(textDto.getRotate());
                text.setZIndex(textDto.getZIndex());
                text.setSize(textDto.getSize());
                text.setText(textDto.getText());
                String decorationDto = textDto.getDecoration();
                switch (decorationDto) {
                    case "line-through" :
                        text.setDecoration(Decoration.LINE_THROUGH);
                    case "overline" :
                        text.setDecoration(Decoration.OVERLINE);
                    case "underline" :
                        text.setDecoration(Decoration.UNDERLINE);
                    case "wavy" :
                        text.setDecoration(Decoration.WAVY);
                    default :
                        text.setDecoration(Decoration.NONE);
                }
                text.setItalic(textDto.isItalic());
                String hrefDto = textDto.getHRef();
                if (hrefDto==null || hrefDto.isBlank()) {
                    text.setHRef(null);
                } else {
                    text.setHRef(hrefDto);
                }
                text.setColor(textDto.getColor());
                text.setName(textDto.getName());
                text.setWeight(textDto.getWeight());
                text.setProject(project);
                textRepository.save(text);
            }

            Canvas canvas = new Canvas();
            CanvasDto canvasDto = projectDto.getCanvas();
            canvas.setPadding(canvasDto.getPadding());
            canvas.setBackgroundImage(canvasDto.getBackgroundImage());
            canvas.setBackgroundColor(canvasDto.getBackgroundColor());
            canvas.setWidth(canvasDto.getWidth());
            canvas.setHeight(canvasDto.getHeight());
            canvasRepository.save(canvas);
            project.setCanvas(canvas);
            project.setUser(user);
            projectRepository.save(project);


            return new StringResponse("Проект " + project.getName() +
                    " успешно добавлен для пользователя " + user.getNickname());

        } catch (Exception e) {
            throw new InputDataErrorException(INVALID_TOKEN);
        }
    }

    public List<ProjectItemResponseDto> getProjects(String token) throws InputDataErrorException {
        try {
            String email = jwtTokenUtil.getUsernameFromToken(token);

            User user = userRepository.findByUsername(email).orElseThrow
                    (() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));

            List<Project> projects = projectRepository.finByUserId(user.getId());
            return projects.stream().map(projectConverter::entityToDto).toList();
        } catch (Exception e) {
            throw new InputDataErrorException(INVALID_TOKEN);
        }
    }
}
