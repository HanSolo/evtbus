package eu.hansolo.evtbus.example;


public class Demo {
    private TopicEvtBus eventBus = new TopicEvtBus();

    public Demo() {
        MyTopic topic1 = new MyTopic("Topic 1");
        MyTopic topic2 = new MyTopic("Topic 2");

        TopicSubscriber subscriber1 = new TopicSubscriber(TopicEvt.NEW_MSG);
        TopicSubscriber subscriber2 = new TopicSubscriber(TopicEvt.UPDATE_MSG);

        eventBus.subscribe(subscriber1);
        eventBus.subscribe(subscriber2);

        eventBus.publish(new TopicEvt(topic1, TopicEvt.NEW_MSG, new Msg("New Msg")));
        eventBus.publish(new TopicEvt(topic2, TopicEvt.UPDATE_MSG, new Msg("Update Msg")));
        eventBus.publish(new TopicEvt(topic2, TopicEvt.NEW_MSG, new Msg("New Msg")));
        eventBus.publish(new TopicEvt(topic1, TopicEvt.UPDATE_MSG, new Msg("Update Msg")));
    }


    public static void main(String[] args) {
        new Demo();
    }
}
