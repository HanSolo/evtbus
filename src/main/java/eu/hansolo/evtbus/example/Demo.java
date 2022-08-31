package eu.hansolo.evtbus.example;


public class Demo {
    private TopicEvtBus eventBus = new TopicEvtBus();

    public Demo() {
        MyTopic topic1 = new MyTopic("Topic 1");
        MyTopic topic2 = new MyTopic("Topic 2");

        TopicSubscriber newMsgSubscriber = new TopicSubscriber("newMsgSubscriber", TopicEvt.NEW_MSG);
        TopicSubscriber updateSubscriber = new TopicSubscriber("updateSubscriber", TopicEvt.UPDATE_MSG);

        eventBus.subscribe(topic1, newMsgSubscriber);
        eventBus.subscribe(topic2, updateSubscriber);

        eventBus.publish(topic1, new TopicEvt(eventBus, TopicEvt.NEW_MSG, new Msg("New Msg")));
        eventBus.publish(topic1, new TopicEvt(eventBus, TopicEvt.UPDATE_MSG, new Msg("Update Msg")));
        eventBus.publish(topic2, new TopicEvt(eventBus, TopicEvt.UPDATE_MSG, new Msg("Update Msg")));
    }


    public static void main(String[] args) {
        new Demo();
    }
}
