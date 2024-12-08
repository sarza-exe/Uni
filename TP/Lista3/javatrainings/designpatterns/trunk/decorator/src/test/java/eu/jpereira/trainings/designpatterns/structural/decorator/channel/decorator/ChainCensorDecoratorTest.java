/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.TwitterChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {

    @Test
    public void testChainTwoDecorators() {
        // Create the builder
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        // create a spy social channel
        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

        // Chain decorators
        SocialChannel channel = builder.
                with(new WordCensor()).
                with(new MessageTruncator(10)).
                with(new URLAppender("http://google.com")).
                getDecoratedChannel(props);

        channel.deliverMessage("Microsoft is a message");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("### is ... http://google.com", spyChannel.lastMessagePublished());
    }

    @Test
    public void testChainTwoDecoratorsWithoutBuilder() {

        TestSpySocialChannel channel = new TestSpySocialChannel();

        SocialChannel urlAppenderChannel = new URLAppender("http://jpereira.eu", channel);

        //Now create a truncator
        SocialChannel messageTruncatorChannel = new WordCensor(urlAppenderChannel);

        messageTruncatorChannel.deliverMessage("Microsoft is a message");
        // Spy channel. Should get the one created before
        assertEquals("### is a message http://jpereira.eu", channel.lastMessagePublished());
    }
}
