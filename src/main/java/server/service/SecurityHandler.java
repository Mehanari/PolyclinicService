package server.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import java.util.Set;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {
    public static final String CLIENT_SECURITY_TOKEN_NAME = "clientToken";
    private final JAXBContext jaxb;

    public SecurityHandler() {
        try {
            jaxb = JAXBContext.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<QName> getHeaders() {
        return Set.of();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        boolean outbound = (boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!outbound) {
            try {
                var headers = soapMessageContext.getMessage().getSOAPHeader();
                headers.examineAllHeaderElements();
                Unmarshaller unmarshaller = jaxb.createUnmarshaller();
                Node clientTokenNode = headers.getElementsByTagName(CLIENT_SECURITY_TOKEN_NAME).item(0);
                String securityToken = (String) unmarshaller.unmarshal(clientTokenNode);
                System.out.println("Inbound message with security token: " + securityToken);
            } catch (SOAPException e) {
                throw new RuntimeException(e);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Inbound message");
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {

    }
}
