import requests
from icalendar import Calendar
from datetime import datetime, timedelta
import pytz
import json
import sys
from dotenv import load_dotenv
import os
from jinja2 import Template

with open('template/events.html') as f:
    template = Template(f.read())
    output = template.render(url='http://localhost:8080/', date='12/15/2023', title='Hello', location='Northridge', time='10:00PM')
    print(output)
