package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum IntegrationSubType {

  EVENT_BRIDGE_PUT_EVENTS("EventBridge-PutEvents"),
  SQS_SEND_MESSAGE("SQS-SendMessage"),
  SQS_RECEIVE_MESSAGE("SQS-ReceiveMessage"),
  SQS_DELETE_MESSAGE("SQS-DeleteMessage"),
  SQS_PURGE_QUEUE("SQS-PurgeQueue"),
  APP_CONFIG_GET_CONFIGURATION("AppConfig-GetConfiguration"),
  KINESIS_PUT_RECORD("Kinesis-PutRecord"),
  STEP_FUNCTIONS_START_EXECUTION("StepFunctions-StartExecution"),
  STEP_FUNCTIONS_STOP_EXECUTION("StepFunctions-StopExecution");

  private final String key;

  IntegrationSubType(String key) {
    this.key = key;
  }

  public String key() {
    return key;
  }

}
