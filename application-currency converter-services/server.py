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
    server = make_server('127.0.0.1', 8000, wsgi_application)
    print("SOAP server starting on http://127.0.0.1:8000...")
    server.serve_forever()
