/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.aws.greengrass.cli.commands;

import com.aws.greengrass.cli.adapter.NucleusAdapterIpc;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "topic", resourceBundle = "com.aws.greengrass.cli.CLI_messages",
        subcommands = CommandLine.HelpCommand.class, mixinStandardHelpOptions = true,
        versionProvider = com.aws.greengrass.cli.module.VersionProvider.class)
public class TopicCommand extends BaseCommand {
    private final NucleusAdapterIpc nucleusAdapterIpc;

    @Inject
    public TopicCommand(NucleusAdapterIpc nucleusAdapterIpc) {
        this.nucleusAdapterIpc = nucleusAdapterIpc;
    }

    @CommandLine.Command(name = "pub", mixinStandardHelpOptions = true,
            versionProvider = com.aws.greengrass.cli.module.VersionProvider.class)
    public int sub(@CommandLine.Option(names = {"-tn", "--topicname"}, paramLabel = "The name of the topic.",
            descriptionKey = "topicname", required = true) String topicName,
                   @CommandLine.Option(names = {"-msg", "--message"}, paramLabel = "The message that is published.",
                           descriptionKey = "topicname", required = true) String message) {
        nucleusAdapterIpc.publishToTopic(topicName, message);

        return 0;
    }
}
