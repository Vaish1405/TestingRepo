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
    directory = "public/"

    if not os.path.exists(directory):
        os.makedirs(directory)

    template = Template(f.read())
    output = template.render(url='https://vaish1405.github.io/', date='12/15/2023', title='Hello', location='Northridge', time='10:00PM')
    print(output)

    with open(os.path.join(directory, 'events.py'), 'w') as file:
        file.write(output, file, indent=4)