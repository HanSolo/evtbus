package eu.hansolo.evtbus.example;

import eu.hansolo.toolbox.evt.EvtType;
import eu.hansolo.toolbox.evt.type.ChangeEvt;

import java.util.Objects;


public class TopicEvt extends ChangeEvt {
    public static final EvtType<TopicEvt> ANY        = new EvtType<>(ChangeEvt.ANY, "ANY");
    public static final EvtType<TopicEvt> NEW_MSG    = new EvtType<>(TopicEvt.ANY, "NEW_MSG");
    public static final EvtType<TopicEvt> UPDATE_MSG = new EvtType<>(TopicEvt.ANY, "UPDATE_MSG");

    private final Msg msg;


    public TopicEvt(final Topic src, final EvtType<TopicEvt> evtType, final Msg msg) {
        super(src, evtType);
        this.msg = msg;
    }


    public Topic getTopic() { return (Topic) getSource(); }

    public Msg getMsg() { return msg; }


    // ******************** Methods *******************************************
    @Override public EvtType<? extends TopicEvt> getEvtType() { return (EvtType<? extends TopicEvt>) super.getEvtType(); }

    @Override public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }
        TopicEvt that = (TopicEvt) o;
        return Objects.equals(that.getMsg(), this.getMsg());
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), msg);
    }
}
