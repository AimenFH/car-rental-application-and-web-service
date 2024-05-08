from wsgiref.simple_server import make_server
from spyne.server.wsgi import WsgiApplication
from spyne.application import Application
from spyne.protocol.soap import Soap11
from service import CurrencyConverterService

application = Application([CurrencyConverterService], 'urn:currency_converter',
                          in_protocol=Soap11(validator='lxml'),
                          out_protocol=Soap11())

if __name__ == '__main__':
    wsgi_application = WsgiApplication(application)
    server = make_server('0.0.0.0', 8000, wsgi_application)
    print("SOAP server starting...")
    server.serve_forever()
