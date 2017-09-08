package com.mkobit.gradle.testkit.assertj;

import org.assertj.core.api.AbstractAssert;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.TaskOutcome;

import javax.annotation.Nullable;

public class BuildTaskAssert extends AbstractAssert<BuildTaskAssert, BuildTask> {
  public BuildTaskAssert(@Nullable final BuildTask actual) {
    super(actual, BuildTaskAssert.class);
  }

  public BuildTaskAssert isFailed() {
    isNotNull();
    assertTaskOutcome(TaskOutcome.FAILED);
    return this;
  }

  public BuildTaskAssert isFromCache() {
    isNotNull();
    assertTaskOutcome(TaskOutcome.FROM_CACHE);
    return this;
  }

  public BuildTaskAssert isNoSource() {
    isNotNull();
    assertTaskOutcome(TaskOutcome.NO_SOURCE);
    return this;
  }

  public BuildTaskAssert isSkipped() {
    isNotNull();
    assertTaskOutcome(TaskOutcome.SKIPPED);
    return this;
  }

  public BuildTaskAssert isSuccess() {
    isNotNull();
    assertTaskOutcome(TaskOutcome.SUCCESS);
    return this;
  }
  public BuildTaskAssert isUpToDate() {
    isNotNull();
    assertTaskOutcome(TaskOutcome.UP_TO_DATE);
    return this;
  }
  public BuildTaskAssert hasTaskOutcome(final TaskOutcome taskOutcome) {
    isNotNull();
    assertTaskOutcome(taskOutcome);
    return this;
  }

  private void assertTaskOutcome(final TaskOutcome expected) {
    if (actual.getOutcome() != expected) {
      //failWithMessage("%nExpecting build output:%n <%s>%nto contain:%n <%s>", text, actual.getOutput());
      failWithMessage(
        "%nExpecting task at path %s to have outcome:%n <%s>%nbut was:%n <%s>",
        actual.getPath(),
        expected,
        actual.getOutcome()
      );
    }
  }
}
