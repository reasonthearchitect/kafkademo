package com.rta.sink.jbehave;

import com.rta.sink.Application;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.*;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.jbehave.core.reporters.Format.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@AcceptanceTest
@WebAppConfiguration
@IntegrationTest("server.port:0")
public abstract class AbstractSpringJBehaveStory extends JUnitStory {

    private static final int STORY_TIMEOUT = 120;

    @Autowired
    private ApplicationContext applicationContext;

    public AbstractSpringJBehaveStory() {
        Embedder embedder = new Embedder();
        embedder.useEmbedderControls(embedderControls());
        embedder.useMetaFilters(Arrays.asList("-skip"));
        useEmbedder(embedder);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryPathResolver(storyPathResolver())
                .useStoryLoader(storyLoader())
                .useStoryReporterBuilder(storyReporterBuilder())
                .useParameterControls(parameterControls());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), applicationContext);
    }

    private EmbedderControls embedderControls() {
        return new EmbedderControls()
                .doIgnoreFailureInView(true)
                .useStoryTimeoutInSecs(STORY_TIMEOUT);
    }

    private ParameterControls parameterControls() {
        return new ParameterControls()
                .useDelimiterNamedParameters(true);
    }

    private StoryPathResolver storyPathResolver() {
        return new UnderscoredCamelCaseResolver();
    }

    private StoryLoader storyLoader() {
        return new LoadFromClasspath();
    }

    private StoryReporterBuilder storyReporterBuilder() {
        return new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                .withPathResolver(new ResolveToPackagedName())
                .withFailureTrace(true)
                .withDefaultFormats()
                .withFormats(IDE_CONSOLE, TXT, HTML);
    }

}
