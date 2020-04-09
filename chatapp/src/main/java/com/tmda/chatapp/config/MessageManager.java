package com.tmda.chatapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

//@Component
public class MessageManager {
    private static final String DELAY_ROU = "_delay";
    private static final Integer DELAY_DUTATION = 1000;
    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class.getName());

    @Resource
    RabbitAdmin rabbitAdmin;

    @Resource
    private RabbitTemplate rabbitTemplate;

//    Configuring the sending format
//
//    @Bean
//    public AmqpTemplate amqpTemplate() {
//        //Use the jackson message converter
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setEncoding("UTF-8");
//
//        // turn on returncallback
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            String correlationId = message.toString();
//            logger.info("message: {} failed to send, response code: {} Cause: {} Switch: {} Routing Key: {}", correlationId, replyCode, replyText, exchange, routingKey);
//        });
//
//        // message confirmation yml needs to be configured
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            if (ack) {
//                logger.info("message sent to exchange successfully");
//            } else {
//                logger.info("Message sent to exchange failed, reason: {}", cause);
//            }
//        });
//        return rabbitTemplate;
//    }
//
////    Creating Dynamic
////    Queues and
////    Switches
//    /**
//     * Send delayed message
//     *
//     * @param object
//     * @param delayTime
//     */
//    public void createQueueAndSend(Message object, Integer delayTime) throws AmqpException {
//
//        DirectExchange exchange = createExchange(rabbitConfig.getExchange() + DELAY_ROU);
//        addExchange(exchange);
//        String delayQueueName = rabbitConfig.getDelay() + "_" + delayTime;
//        Queue delayQueue = createDelayQueue(delayQueueName, delayTime);
//        addQueue(delayQueue);
//        addBinding(delayQueue, exchange, delayQueueName);
//
//        rabbitTemplate.convertAndSend(delayQueueName, object, new CorrelationData(object.getTag()));
//
//    }
//
//    /**
//     * According to the send delay message
//     *
//     * @param object
//     * @param secondDelayTime unit seconds
//     */
//    public void sendDelayMessage(Message object, Integer secondDelayTime) throws AmqpException {
//
//        String queueSerial = NotifyDailyEnum.getDescByCode(secondDelayTime);
//        if (StringUtils.isNoneBlank(queueSerial)) {
//            String delayQueueName = rabbitConfig.getDelay() + "_" + queueSerial;
//            rabbitTemplate.convertAndSend(delayQueueName, object, new CorrelationData(object.getTag()));
//        } else {
//            createQueueAndSend(object, secondDelayTime * DELAY_DUTATION);
//        }
//
//    }
//
//    /**
//     * Send messages based on que and rout
//     *
//     * @param routingKey
//     * @param object
//     */
//    public void sendQueueMessage(String routingKey, Message object) {
//
////        rabbitTemplate.convertAndSend(routingKey, object, new CorrelationData(object..getTag()));
//    }
//
//    /**
//     * sent according to exhange and rout
//     *
//     * @param exchange
//     * @param routingKey
//     * @param object
//     */
//    public void sentExchangeMessage(String exchange, String routingKey, Object object) {
//
//        rabbitTemplate.convertAndSend(exchange, routingKey, object);
//    }
//
//    /**
//     * Create Exchange
//     *
//     * @param exchange
//     */
//    private void addExchange(AbstractExchange exchange) {
//        rabbitAdmin.declareExchange(exchange);
//    }
//
//
//    /**
//     * Create a specified Queue
//     *
//     * @param queue
//     * @return queueName
//     */
//    private String addQueue(Queue queue) {
//        return rabbitAdmin.declareQueue(queue);
//    }
//
//    /**
//     * Bind a queue to a matching switch using a routingKey
//     *
//     * @param queue
//     * @param exchange
//     * @param routingKey
//     */
//    private void addBinding(Queue queue, DirectExchange exchange, String routingKey) {
//        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
//        rabbitAdmin.declareBinding(binding);
//    }
//
//
//    private Queue createDelayQueue(String queueName, Integer delayMillis) {
//        /**
//         * Queue name // dead letter time, dead letter re-delivery switch, route to queue routingKey
//         */
//        return QueueBuilder.durable(queueName)
//                .withArgument("x-message-ttl", delayMillis)
//                .withArgument("x-expires", delayMillis * DELAY_LIFE) //Set the queue auto delete time
//                .withArgument("x-dead-letter-exchange", rabbitConfig.getExchange())
//                .withArgument("x-dead-letter-routing-key", rabbitConfig.getQueue())
//                .build();
//    }
//
//    /**
//     * Create queues that are not deleted
//     *
//     * @param queueName
//     * @param delayMillis
//     * @return
//     */
//    private Queue createDelayQueueNoDelete(String queueName, Integer delayMillis) {
//        /**
//         * Queue name // dead letter time, dead letter re-delivery switch, route to queue routingKey
//         */
//        return QueueBuilder.durable(queueName)
//                .withArgument("x-message-ttl", delayMillis)
//                .withArgument("x-dead-letter-exchange", rabbitConfig.getExchange())
//                .withArgument("x-dead-letter-routing-key", rabbitConfig.getQueue())
//                .build();
//    }
//
//    private DirectExchange createExchange(String exchangeName) {
//        return new DirectExchange(exchangeName, true, false);
//    }
}