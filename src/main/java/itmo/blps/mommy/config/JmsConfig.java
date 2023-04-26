package itmo.blps.mommy.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;


@Configuration
@AllArgsConstructor
public class JmsConfig {
    private RabbitConfig rabbitConfig;

    @Bean
    Queue queue() {
        return new Queue(rabbitConfig.getQueueName(), false);
    }

    @Bean
    JmsTemplate jmsTemplate() {
        return new JmsTemplate();
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(rabbitConfig.getQueueName());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("message.#");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory ccf = new CachingConnectionFactory(rabbitConfig.getHost());
        ccf.setPort(Integer.parseInt(rabbitConfig.getPort()));
        ccf.setUsername(rabbitConfig.getUsername());
        ccf.setPassword(rabbitConfig.getPassword());
        return ccf;
    }
}
